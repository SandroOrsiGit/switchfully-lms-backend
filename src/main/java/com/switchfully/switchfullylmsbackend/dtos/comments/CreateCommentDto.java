package com.switchfully.switchfullylmsbackend.dtos.comments;


public class CreateCommentDto {
	private String text;
	private Long codelabId;

	public CreateCommentDto() {
	}

	public CreateCommentDto(String text, Long codelabId) {
		this.text = text;
		this.codelabId = codelabId;
	}
	public String getText() {
		return text;
	}

	public Long getCodelabId() {
		return codelabId;
	}
}
