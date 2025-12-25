package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.ScoresDao;
import io.renren.modules.generator.entity.ScoresEntity;
import io.renren.modules.generator.service.ScoresService;


@Service("scoresService")
public class ScoresServiceImpl extends ServiceImpl<ScoresDao, ScoresEntity> implements ScoresService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ScoresEntity> page = this.page(
                new Query<ScoresEntity>().getPage(params),
                new QueryWrapper<ScoresEntity>()
        );

        return new PageUtils(page);
    }

}