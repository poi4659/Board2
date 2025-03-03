package jin.spring.board.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {
//	게시글 번호
	private int bdNum;
//	게시글 제목
	private String bdTitle;
//	게시글 내용
	private String bdContent;
//	게시글 작성자
	private String bdWriter;
//	게시글 등록일
	private Date regDate;
}