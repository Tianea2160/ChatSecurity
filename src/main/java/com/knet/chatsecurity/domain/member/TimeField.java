package com.knet.chatsecurity.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class TimeField {
    @CreatedDate
    private LocalDateTime created_at;

    private LocalDateTime leaved_at;

}
