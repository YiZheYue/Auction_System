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
@TableName("students")
public class StudentsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 学号
	 */
	@TableId
	private Integer studentId;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 班级
	 */
	private String className;

}
