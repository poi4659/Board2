// 페이지 번호와 게시글수에 맞는 쿼리 문자열을 만듦
// 이전/다음 버튼 만듦 
package jin.spring.board.dto;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
public class PageMaker {
//	전체 게시글 수
	private int totalCount;
	
//	현재 페이지 블록의 시작 페이지 번호 
	private int startPage;
	
//	현재 페이지 블록의 끝 페이지 번호 
	private int endPage;
	
//	이전 페이지 블록이 있는지 여부 
	private boolean prev;
	
//	다음 페이지 블록이 있는지 여부 
	private boolean next;
	
//	한번에 보여줄 페이지 수  
	private int displayPageNum = 10;
	
//	현재 페이지 정보와 페이지당 항목 수를 담고 있는 객체 
	private Criteria cri;
	
//	Criteria 객체를 설정
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
//	전체 게시글 수를 받아서 저장하고, calcData()를 호출해 페이징 정보를 계산 
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

//	현재 페이지 번호 기준으로 현재 블록의 마지막 페이지 번호를 계산 
	private void calcData() {
//		예: page = 7, displayPageNum = 10이면 
//		(7 / 10.0 = 0.7) → ceil(0.7) = 1 → 1 * 10 = 10
//		즉 endPage = 10.
		endPage = (int)(Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
		
//		블록의 첫 번째 페이지 번호 계산. 위 예에서 startPage = 1
		startPage = (endPage - displayPageNum) + 1;
		
//		전체 게시글 수 기준으로 전체 페이지 수를 계산
//		예: 게시글 95개이고 페이지당 10개면 ceil(95 / 10) = 10.
		int tempEndPage = (int)(Math.ceil(totalCount / (double)cri.getPerPageNum()));
		
//		계산된 endPage가 실제 마지막 페이지를 초과하지 않도록 조정
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
//		이전 페이지 블록 유무 (startPage가 1이면 이전 블록 없음)
		prev = startPage == 1? false : true;
		
//		다음 페이지 블록 유무 (endPage * 페이지당 게시글 수 >= 전체 게시글 수이면 다음 블록 없음)
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}

//	페이지 버튼을 눌렀을때 이동할 URL 쿼리 문자열을 만듦 
	public String makeQuery(int page) {
		UriComponents uriComponents = 
//				URI 조합을 위한 빌더 인스턴스를 생성
				UriComponentsBuilder.newInstance()
//									쿼리 문자열에 page 값을 추가함
									.queryParam("page", page)
//									한 페이지에 몇개 항목을 보여줄 것인지 추가 
									.queryParam("perPageNum", cri.getPerPageNum())
//									모든 쿼리 파라미터를 조합하여 객체 생성 
									.build();
//		.toUriString():	최종적으로 URI 형식의 문자열로 변환 
		return uriComponents.toUriString();
	}
}
