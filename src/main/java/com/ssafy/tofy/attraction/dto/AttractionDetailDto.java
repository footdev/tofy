package com.ssafy.tofy.attraction.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AttractionDetailDto {
	private AttractionDescDto attractionDesc;
	private AttractionDto attractionDto;
	@Override
	public String toString() {
		return "AttractionDetailDto [attractionDesc=" + attractionDesc.toString() + ", attractionDto=" + attractionDto.toString() + "]";
	}
}
