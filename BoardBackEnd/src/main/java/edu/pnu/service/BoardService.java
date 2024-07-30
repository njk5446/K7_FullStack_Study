package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
// final 필드와 @NonNull 애노테이션이 적용된 필드들을 매개변수로 갖는 생성자를 자동으로 생성
public class BoardService {
	private final BoardRepository boardRepo;
	
	public List<Board> getBoards() {
		return boardRepo.findAll();
	}
	
	public List<Board> getBoard(Long id) {
		List<Board> temp = new ArrayList<>();
		Board getBoard = boardRepo.findById(id).get();
		temp.add(getBoard);
		return temp;
	}
	
	public List<Board> insertBoard(Board board) {
		boardRepo.save(Board.builder().title(board.getTitle())
									  .content(board.getContent())
									  .writer(board.getWriter())
									  .createDate(new Date())
									  .build());
		return boardRepo.findAll();
	}
	
	public List<Board> updateBoard(Long id, Board reviseBoard) {
		Board b = boardRepo.findById(id).get();
		//findById: 수정할 board의 seq를 찾는다
		if (b == null) // 수정할 board가 비어있으면
			return null; // nullPointerException 발생
		b.setTitle(reviseBoard.getTitle()); // 수정
		b.setContent(reviseBoard.getContent()); // 수정
		b.setWriter(reviseBoard.getWriter());
		b.setCreateDate(new Date()); // 수정날짜로 수정
		boardRepo.save(b); //수정한 board를 저장소에 다시 붙인다
		return boardRepo.findAll();
	}
	
	public List<Board> deleteBoard(Long id) {
		//@PathVariable: url에서 ?변수명을 적지않고 값만 적어도 됨
		Board deleteBoard = boardRepo.findById(id).get(); //먼저 수정할 Board의 seq를 찾고, get()을 통해 해당 타입으로 캐스팅한다.
		// get(): Optional 내장객체의 메서드로, 변수로 선언한 타입으로 자동 캐스팅해준다.. 
		if (deleteBoard == null) // 해당 board가 비어있으면 nullPointerException 발생
			return null;
		boardRepo.deleteById(deleteBoard.getId()); 
		// 먼저 게시판안의 삭제하고싶은 deleteBoard의 seq를 찾는다
		// boardRepo는 게시판이고, boardRepo.deleteById(삭제할 게시글.getSeq())
		List<Board> temp = new ArrayList<>();
		temp.add(deleteBoard);
		return temp; //삭제된 게시글을 화면에 출력
	}
}
