/*
 *www.dyr.com
 *Copyright (c) 2014 All Rights Reserved
 */

package com.team3.mbts.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * Project:moviebookticketsystem
 * Package:com.team3.mbts.entity
 * FileName:Movie.java 影片表
 * Comments:
 * JDK Version:
 * Author : LongJun
 * Create Date:2015-1-12 上午10:40:35
 * Modified By : 徐晓聪
 * Modified Time:2015-2-10 20:55:24
 * What is Modified:
 * Version:
 */
public class Movie implements Serializable {
	private static final long serialVersionUID = -5520753995512213891L;
	
	/**
	 * 影片编号
	 */
	private int movieId;
	/**
	 * 影片名称
	 */
	private String name;
	/**
	 * 影片海报
	 */
	private String post;
	/**
	 * 影片导演
	 */
	private List<Director> directors;
	/**
	 * 影片主演列表
	 */
	private List<Actor> actors;
	/**
	 * 影片标题
	 */
	private String title;
	/**
	 * 影片类型列表
	 */
	private List<MovieType> movieTypes;
	/**
	 * 影片是否是3D(默认不是)
	 */
	private boolean is3D = false;
	/**
	 * 影片地区（默认中国）
	 */
	private String area = "中国";
	/**
	 * 影片时长
	 */
	private int duration;	//时长，单位为分钟
	/**
	 * 影片语言
	 */
	private String language;
	/**
	 * 影片上映时间
	 */
	private Date publish;
	/**
	 * 影片描述
	 */
	private String description;
	/**
	 * 影片喜欢数
	 */
	private int likeCount = 0;
	/**
	 * 影片受关注数
	 */
	private int attentionCount = 0;
	/**
	 * 影片购票数
	 */
	private int buyCount = 0;
	
	/**
	 * 评价得分
	 */
	private Float score = 7.0f;
	/**
	 * 影片是否存在，未被删除，默认为true 
	 */
	private boolean isExist = true;
	
	public Movie() {
		super();
	}
	
	public Movie(String name, String post,  String title,
			boolean is3d, String area, int duration, String language,
			Date publish, String description) {
		super();
		this.name = name;
		this.post = post;
		this.title = title;
		is3D = is3d;
		this.area = area;
		this.duration = duration;
		this.language = language;
		this.publish = publish;
		this.description = description;		
	}
	
	public Movie(String name, String post, List<Director> directors, String title,
			boolean is3d, String area, int duration, String language,
			Date publish, String description) {
		super();
		this.name = name;
		this.post = post;
		this.directors = directors;
		this.title = title;
		is3D = is3d;
		this.area = area;
		this.duration = duration;
		this.language = language;
		this.publish = publish;
		this.description = description;		
	}
	
	public int getMovieId() {
		return movieId;
	}
	
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPost() {
		return post;
	}
	
	public void setPost(String post) {
		this.post = post;
	}			
	
	public boolean isIs3D() {
		return is3D;
	}
	
	public void setIs3D(boolean is3d) {
		is3D = is3d;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}	
		
	public List<Director> getDirectors() {
		return directors;
	}

	public void setDirectors(List<Director> directors) {
		this.directors = directors;
	}

	public List<Actor> getActors() {
		return actors;
	}
	
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	
	public List<MovieType> getMovieTypes() {
		return movieTypes;
	}
	
	public void setMovieTypes(List<MovieType> movieTypes) {
		this.movieTypes = movieTypes;
	}
	
	public String getArea() {
		return area;
	}
	
	public void setArea(String area) {
		this.area = area;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public Date getPublish() {
		return publish;
	}
	
	public void setPublish(Date publish) {
		this.publish = publish;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getLikeCount() {
		return likeCount;
	}
	
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	public int getAttentionCount() {
		return attentionCount;
	}
	
	public void setAttentionCount(int attentionCount) {
		this.attentionCount = attentionCount;
	}
	
	public int getBuyCount() {
		return buyCount;
	}
	
	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}			
	
	public Float getScore() {
		return score;
	}
	
	public void setScore(Float score) {
		this.score = score;
	}
	
	public boolean isExist() {
		return isExist;
	}

	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"movieId\":" + this.movieId + ",");
		sb.append("\"name\":\"" + this.name + "\",");
		sb.append("\"post\":\"" + this.post + "\",");
		sb.append("\"types\":\"" + this.getMovieTypesStr() + "\",");
		sb.append("\"language\":\"" + this.language + "\",");
		sb.append("\"director\":\"" + this.getDirectorsStr()+ "\",");
		sb.append("\"actors\":\"" + this.getActorsStr()+ "\",");
		sb.append("\"duration\":" + this.duration+ ",");
		sb.append("\"title\":\"" + this.title+ "\",");
		sb.append("\"attentionCount\":" + this.attentionCount + ",");
		sb.append("\"buyCount\":" + this.buyCount);
		sb.append("\"score\":" + this.score + ",");
		sb.append("}");
		return sb.toString();
	}
	
	/**
	 * 获取电影主演名的连接串
	 * @return
	 */
	private String getActorsStr() {
		StringBuffer buffer = new StringBuffer();
		for(Actor actor : this.actors) {
			buffer.append(actor.getActorName() + " ");
		}
		if(buffer.length() == 0) {
			return "";
		} else {
			return buffer.subSequence(0, buffer.length()-1).toString();
		}
	}
	
	/**
	 * 获取电影导演名的连接串
	 * @return
	 */
	private String getDirectorsStr() {
		StringBuffer buffer = new StringBuffer();
		for(Director director : this.directors) {
			buffer.append(director.getDirectorName() + " ");
		}
		if(buffer.length() == 0) {
			return "";
		} else {
			return buffer.subSequence(0, buffer.length()-1).toString();
		}
	}
	
	/**
	 * 获取电影类型名的连接串
	 * @return
	 */
	private String getMovieTypesStr() {
		StringBuffer buffer = new StringBuffer();
		for(MovieType type : this.movieTypes) {
			buffer.append(type.getTypeName() + " ");
		}
		if(buffer.length() > 0) {
			return buffer.subSequence(0, buffer.length()-1).toString();
		} else {
			return "";
		}
	}
	
}
