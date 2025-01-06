package com.team5.campscore.dao;

import org.apache.ibatis.annotations.Mapper;

import com.team5.campscore.model.LikeDTO;

@Mapper
public interface LikeDAO {

	public int insertLike(LikeDTO likeDTO);

	public int deleteLike(LikeDTO likeDTO);

	public int getCount(LikeDTO likeDTO);

	

}
