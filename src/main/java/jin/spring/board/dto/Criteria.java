// 페이징 처리를 위한 핵심 정보를 담는 기준 클래스 
// 지금 몇 페이지를 보고 있는지, 한 페이지에 몇 개씩 보여줄건지 등
package jin.spring.board.dto;

public class Criteria {
//	현재 페이지 번호
	private int page;
//	페이지당 출력할 게시글 개수
	private int perPageNum;
//	현재 페이지에서 가져올 첫번째 게시글 번호
	private int rowStart;
//	현재 페이지에서 가져올 마지막 게시글 번호
	private int rowEnd;

//	오류나서 수정함 
//  기본 생성자여야 하는데 파라미터 생성자로 작성해서 오류남 
    public Criteria() {
        // 기본값 설정
        this.page = 1;
        this.perPageNum = 10;
    }

	public void setPage(int page) {
//		페이지 값이 0 이하일 경우
		if (page <= 0) {
//			강제로 1 설정: 잘못된 요청 방지 
			this.page = 1;
			return;
		}

		this.page = page;
	}


	public void setPerPageNum(int perPageNum) {
//		한 페이지당 보여줄 개수를 1~100 사이로 제한
		if (perPageNum <= 0 || perPageNum > 100) {
//			범위 벗어나면 기본값 10으로 설정
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}


//	현재 페이지 번호 반환 
	public int getPage() {
		return page;
	}
	
//	현재 페이지의 첫번째 게시글의 시작 위치 계산
//	데이터베이스에서 가져올 데이터의 시작 위치 계산
	public int getPageStart() {
		/*
		 * (현재 페이지 - 1) * perPageNum
		 * 예) page = 1, perPageNum = 10 → (1-1) * 10 = 0 page = 2, perPageNum = 10 →
		 * (2-1) * 10 = 10
		 */
		return (this.page - 1) * perPageNum;
	}

//	페이지당 게시글 수를 반환
	public int getPerPageNum() {
		return this.perPageNum;
	}

//	시작 행 계산
	public int getRowStart() {
		/*
		 * 현재 페이지에서 가져올 데이터의 첫 번째 행 번호를 계산 1부터 시작해야해서 +1 함 
		 * 예) 3페이지-> (3-1) * 10 + 1 =>
		 * 21번이 시작 행
		 */
		rowStart = ((page - 1) * perPageNum) + 1;
		return rowStart;
	}

//	끝 행 번호 계산
	public int getRowEnd() {
		/*
		 * 현재 페이지에서 가져올 마지막 행 번호를 계산 
		 * 예) 3페이지-> 21 + 10 - 1 => 30번이 마지막 행
		 */
		rowEnd = rowStart + perPageNum - 1;
		return rowEnd;
	}

//	출력할 때 편하게 보기 위한 메서드 
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", rowStart=" + rowStart + ", rowEnd=" + rowEnd
				+ "]";
	}

}
