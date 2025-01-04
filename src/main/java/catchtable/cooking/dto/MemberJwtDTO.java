package catchtable.cooking.dto;

import catchtable.cooking.persist.domain.Member;
import catchtable.cooking.persist.domain.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberJwtDTO {

    private Long id;

    private String nickname;

    private MemberRole role;

    public MemberJwtDTO of(Member member) {
        return MemberJwtDTO.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .role(member.getRole())
                .build();
    }
}
