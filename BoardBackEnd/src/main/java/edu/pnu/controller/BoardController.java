package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardController {
	
	@Autowired
	private final BoardService boardService;
	
	@GetMapping("/board")
	public ResponseEntity<?> getBoard() {
		log.info("getBoard: All");
		return ResponseEntity.ok(boardService.getBoards());
	}
	
	@GetMapping("/board/{id}")
	public ResponseEntity<?> getBoard(@PathVariable Long id) {
		log.info("getBoard: " + id);
		return ResponseEntity.ok(boardService.getBoard(id));
	}
	
	@PostMapping("/board")
	public ResponseEntity<?> postBoard(@RequestBody Board board) {
		log.info("insertBoard: ");
		return ResponseEntity.ok(boardService.insertBoard(board));
	}
	
	@PutMapping("/board/{id}")
	public ResponseEntity<?> putBoard(@PathVariable Long id, @RequestBody Board reviseBoard) {
		log.info("updateBoard: ");
		return ResponseEntity.ok(boardService.updateBoard(id, reviseBoard));
	}
	
	@DeleteMapping("/board/{id}") //
	public ResponseEntity<?> deleteBoard(@PathVariable Long id) { //파라미터(url)에 수정하려는 board의 seq를 입력
		log.info("insertBoard: ");
		return ResponseEntity.ok(boardService.deleteBoard(id));
		
	}
	

	

}
