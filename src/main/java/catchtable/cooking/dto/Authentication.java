package catchtable.cooking.dto;

import catchtable.cooking.persist.domain.Member;
import catchtable.cooking.persist.domain.MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authentication {

    private String nickname;

    private MemberType role;

    public Authentication of(Member member) {
        return Authentication.builder()
                .nickname(member.getNickname())
                .role(member.getRole())
                .build();
    }

}