package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.StudentsDao;
import io.renren.modules.generator.entity.StudentsEntity;
import io.renren.modules.generator.service.StudentsService;


@Service("studentsService")
public class StudentsServiceImpl extends ServiceImpl<StudentsDao, StudentsEntity> implements StudentsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<StudentsEntity> page = this.page(
                new Query<StudentsEntity>().getPage(params),
                new QueryWrapper<StudentsEntity>()
        );

        return new PageUtils(page);
    }

}