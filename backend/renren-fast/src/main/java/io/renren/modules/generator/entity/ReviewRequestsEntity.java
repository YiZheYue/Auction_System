package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2024-11-16 12:01:41
 */
@Data
@TableName("review_requests")
public class ReviewRequestsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 反馈编号
	 */
	@TableId
	private Integer requestId;
	/**
	 * 学号
	 */
	private Integer studentId;
	/**
	 * 考试编号
	 */
	private Integer examId;
	/**
	 * 科目
	 */
	private String subject;
	/**
	 * 处理状态
	 */
	private Enum status;
	/**
	 * 反馈原因
	 */
	private String reason;
	/**
	 * 处理回复
	 */
	private String response;

}
