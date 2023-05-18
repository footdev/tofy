package com.ssafy.tofy.worldcup.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WorldCupResult {

    String worldCupNo;
    String userNo;
    String contentId;
    String worldCupCreate;

}
