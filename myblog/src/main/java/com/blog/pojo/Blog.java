package com.blog.pojo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
public class Blog {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ;
@NotEmpty
@Size(min = 2 , max = 50)
@Column(nullable = false , length = 50)
	private String title;
	@NotEmpty
	@Size(min = 2 , max = 300)
	@Column(nullable = false)
	private String subTitle;
	@Lob
	@Column(name = "content",nullable= false)
	@Basic(fetch = FetchType.EAGER)
	@NotEmpty
	@Size(min=2)
	private String content;
	@Column(nullable = false)
	@CreationTimestamp
	private Timestamp createTime;
	@Column(name = "readSize")
	private int readSize = 0 ;
	@Column(name = "commentSize")
	private int commentSize =0 ;
	@Column(name = "voteSize")
	private int voteSize;
	@Column(name = "tags",length = 100)
	private String tags;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	@OneToMany(mappedBy = "blog")
	private List<Review> reviews ;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "blog_id")
	private List<Vote> votes ; 
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "cateogry_id")
	private Category category ;
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Vote> getVotes() {
		return votes;
	}
	public void setVotes(List<Vote> votes) {
		this.votes = votes;
		this.voteSize = this.votes.size();
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public int getReadSize() {
		return readSize;
	}
	public void setReadSize(int readSize) {
		this.readSize = readSize;
	}
	public int getCommentSize() {
		return commentSize;
	}
	public void setCommentSize(int commentSize) {
		this.commentSize = commentSize;
	}
	public int getVoteSize() {
		return voteSize;
	}
	public void setVoteSize(int voteSize) {
		this.voteSize = voteSize;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
