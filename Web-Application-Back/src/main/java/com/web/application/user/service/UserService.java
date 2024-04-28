package com.web.application.user.service;

import com.web.application.auth.dto.response.TokenInfo;
import com.web.application.user.dto.request.UserProjectUpdateRequest;
import com.web.application.user.dto.response.ProjectResponse;
import com.web.application.user.dto.request.UserPasswordChangeRequest;
import com.web.application.user.dto.response.CustomerResponse;
import com.web.application.user.entity.User;
import com.web.application.user.entity.UserProject;
import com.web.application.user.repository.UserProjectRepository;
import com.web.application.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserProjectRepository userProjectRepository;
    private final PasswordEncoder passwordEncoder;

    // 사용자 비밀번호 변경
    @Transactional
    public void userPasswordChange(final Long userSeq,
                                   final UserPasswordChangeRequest requestDTO){

        // 비밀번호를 바꾸려는 사용자 찾기
        User targetUser = userRepository.findById(userSeq).orElseThrow(NoSuchElementException::new);
        if(!passwordEncoder.matches(requestDTO.getCurrentPassword(), targetUser.getUserPassword())){
            // 현재 비밀번호와 request 비밀번호가 다른경우 exception (exception 나중에 수정 필요)
            throw new NoSuchElementException();
        }
        // 현재 비밀번호 새로운 비밀번호로 변경
        targetUser.updateUserPassword(passwordEncoder.encode(requestDTO.getNewPassword()));

        userRepository.save(targetUser);
    }

    // 고객사 options get
    public List<CustomerResponse> getCustomerOptions(final TokenInfo tokenInfo){
        List<UserProject> targetList = userProjectRepository.findByEmployeeNumber(tokenInfo.getEmployeeNumber());

        return targetList.stream()
                .map(target -> new CustomerResponse(target.getCustomerCode(), target.getCustomerName()))
                .distinct()
                .toList();
    }


    // 프로젝트 options get
    public List<ProjectResponse> getProjectOptions(final TokenInfo tokenInfo,
                                                   final String customerCode){

        if(customerCode.equals("")){
            throw new NoSuchElementException();
        }

        List<UserProject> targetList = userProjectRepository.findByEmployeeNumberAndCustomerCode(tokenInfo.getEmployeeNumber(), customerCode);

        return targetList.stream()
                .map(target -> new ProjectResponse(target.getRegisterCode(),target.getCorporationCode(), target.getCorporationName(), target.getProjectCode(), target.getProjectName()))
                .toList();
    }

    // 프로젝트 선택 update
    @Transactional
    public void updateUserProject(final TokenInfo tokenInfo,
                                  final UserProjectUpdateRequest request){

        User targetUser = userRepository.findById(tokenInfo.getUserSeq()).orElseThrow(NoSuchElementException::new);
        targetUser.updateUserProject(request.getRegisterCode());

        userRepository.save(targetUser);
    }

    // 회원/계정 탈퇴
    @Transactional
    public void deleteUser(final TokenInfo tokenInfo){

        userRepository.deleteById(tokenInfo.getUserSeq());

    }
}
