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
@TableName("scores")
public class ScoresEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 成绩编号
	 */
	@TableId
	private Integer scoreId;
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
	 * 分数
	 */
	private Integer score;

}
