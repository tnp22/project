package com.aloha.project.service;

import java.util.List;

import com.aloha.project.domain.Board;
import com.github.pagehelper.PageInfo;


public interface BoardService {

    public PageInfo<Board> list(int page, int size) throws Exception;

    public List<Board> list() throws Exception;

    public Board select(int no) throws Exception;

    public int insert(Board board) throws Exception;

    public int update(Board board) throws Exception;

    public int delete(int no) throws Exception;
}
