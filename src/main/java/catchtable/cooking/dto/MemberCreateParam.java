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
public class MemberCreateParam {

    private String name;

    private String email;

    private String nickname;

    private String password;

    private String phoneNumber;

    private MemberType role;

    public MemberCreateParam of(MemberRequest memberRequest) {
        return MemberCreateParam.builder()
                .name(memberRequest.getName())
                .email(memberRequest.getEmail())
                .nickname(memberRequest.getNickname())
                .password(memberRequest.getPassword())
                .phoneNumber(memberRequest.getPhoneNumber())
                .role(memberRequest.getRole()).build();
    }


}
