package com.aloha.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aloha.project.domain.Board;
import com.aloha.project.domain.Page;
import com.aloha.project.service.BoardService;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;


@Slf4j  
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;   


    @GetMapping("/list")
    public String list(Model model
                      ,@RequestParam(name = "page", required = false, defaultValue = "1") Integer page
                      ,@RequestParam(name = "size", required = false, defaultValue = "10") Integer size) throws Exception {
        // 데이터 요청
        PageInfo<Board> pageInfo = boardService.list(page, size);
        
        Page page1 = new Page();
        page1.setPage(page);
        page1.setSize(size);
        page1.setTotal( pageInfo.getTotal() );

        // 모델 등록
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("page", page1);
        // 뷰 페이지 지정
        return "/board/list";       // resources/templates/board/list.html
    }
    

    @GetMapping("/read")
    public String read(@RequestParam("no") int no, Model model) throws Exception {

        Board board = boardService.select(no);
        model.addAttribute("board", board);
        return "/board/read";
    }

    @GetMapping("/insert")
    public String insert() {

        return "/board/insert";
    }


    @PostMapping("/insert")
    public String insertPro(Board board) throws Exception {

        int result = boardService.insert(board);

        if( result > 0 ) {
            return "redirect:/board/list";
        }
        return "redirect:/board/insert?error";  
    }
    

    @GetMapping("/update")
    public String update(@RequestParam("no") int no, Model model) throws Exception {
        Board board = boardService.select(no);
        model.addAttribute("board", board);
        return "/board/update";
    }


    @PostMapping("/update")
    public String updatePro(Board board) throws Exception {
        int result = boardService.update(board);

        if( result > 0 ) {
            return "redirect:/board/list";
        }
        int no = board.getNo();
        return "redirect:/board/update?no="+ no + "&error";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("no") int no) throws Exception {
        int result = boardService.delete(no);
        if( result > 0 ) {
            return "redirect:/board/list";
        }
        return "redirect:/board/update?no=" + no + "&error";
    }
}
