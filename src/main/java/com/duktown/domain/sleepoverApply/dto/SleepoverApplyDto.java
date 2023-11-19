package com.duktown.domain.sleepoverApply.dto;

import com.duktown.domain.BaseTimeEntity;
import com.duktown.domain.sleepoverApply.entity.SleepoverApply;
import com.duktown.domain.user.entity.User;
import com.duktown.global.type.ApprovalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public class SleepoverApplyDto extends BaseTimeEntity {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestSleepoverApplyDto{

        @NotNull
        @FutureOrPresent(message = "외박 신청 날짜는 현재 시간 이후거나 동일해야 합니다.")
        private LocalDate startDate; //외박 시작 날짜
        @NotNull
        @Future(message = "귀가 날짜는 미래여야 합니다.")
        private LocalDate endDate; //돌아오는 날짜

        @Min(value = 1)
        private Integer period; //외박 일 수
        @NotBlank(message = "머무는 곳의 주소는 필수 값입니다")
        private String zipcode;
        private String streetAddress;// 지번 주소
        private String detailAddress;// 상세 주소
        @NotBlank(message = "사유는 필수 값입니다.")
        private String reason; //사유

        public SleepoverApply toEntity(User user){
            return SleepoverApply.builder()
                    .user(user)
                    .startDate(startDate)
                    .endDate(endDate)
                    .period(period)
                    .address(zipcode+streetAddress+detailAddress)
                    .reason(reason)
                    .approved(ApprovalType.Waiting)
                    .build();
        }
    }


    @Getter
    @Builder
    @AllArgsConstructor
    public static class ResponseGetListSleepoverApply{
        private Integer sleepoverApplyCount;
        private List<ResponseGetSleepoverApply> listSleepoverApply;

        public static ResponseGetListSleepoverApply from(List<ResponseGetSleepoverApply> getSleepoverApplyList){
            return ResponseGetListSleepoverApply.builder()
                    .sleepoverApplyCount(getSleepoverApplyList.size())
                    .listSleepoverApply(getSleepoverApplyList)
                    .build();
        }
    }


    @Getter
    @AllArgsConstructor
    public static class ResponseGetSleepoverApply{
        private Long userId;
        private LocalDate startDate;
        private LocalDate endDate;
        private Integer period; //외박 일 수
        private String address; // 머무르는 주소
        private String reason; //사유

       public ResponseGetSleepoverApply(SleepoverApply sleepoverApply){
           startDate = sleepoverApply.getStartDate();
           endDate= sleepoverApply.getEndDate();
           period= sleepoverApply.getPeriod();
           address=  sleepoverApply.getAddress();
           userId= sleepoverApply.getUser().getId();
           reason =sleepoverApply.getReason();

        }
    }



}
