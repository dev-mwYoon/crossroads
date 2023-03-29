package com.crossroads.app.domain.dao;

import com.crossroads.app.domain.dto.ReplyDTO;
import com.crossroads.app.domain.dto.ReviewDTO;
import com.crossroads.app.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {
    private final ReplyMapper replyMapper;

//    게시글 별 댓글 수 조회
    public Integer findReplyCount(Long boardId) {
        return replyMapper.selectReplyCount(boardId);
    }


//    마이페이지 후기 전체 조회
    public List<ReplyDTO> findAllMyReply(Long memberId){
        return replyMapper.selectAllMyReply(memberId);
    }

}
