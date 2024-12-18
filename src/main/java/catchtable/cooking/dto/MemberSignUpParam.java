package catchtable.cooking.dto;

import catchtable.cooking.persist.domain.MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignUpParam {

    private String name;

    private String email;

    private String nickname;

    private String password;

    private String phoneNumber;

    private MemberType role;

    public MemberSignUpParam of(MemberSignUpRequest memberSignUpRequest) {
        return MemberSignUpParam.builder()
                .name(memberSignUpRequest.getName())
                .email(memberSignUpRequest.getEmail())
                .nickname(memberSignUpRequest.getNickname())
                .password(memberSignUpRequest.getPassword())
                .phoneNumber(memberSignUpRequest.getPhoneNumber())
                .role(memberSignUpRequest.getRole()).build();
    }


}
