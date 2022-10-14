package com.bilgeadam.commentapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeCreateRequestDto {

    private  Long userId;
    private  Long productId;

}
