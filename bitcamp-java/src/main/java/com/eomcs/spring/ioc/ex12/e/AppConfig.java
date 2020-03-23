package com.eomcs.spring.ioc.ex12.e;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import com.eomcs.spring.ioc.ex12.Board;
//Spring IoC 컨테이너에서 사용할 Properties 파일을 로딩하기
@PropertySource("classpath:com/eomcs/spring/ioc/ex12/jdbc.properties")
// Mybatis DAO 프록시를 자동 생성할 인터페이스를 지정하기
@MapperScan("com.eomcs.spring.ioc.ex12.e")
public class AppConfig {
  @Value("${jdbc.driver}")
  String jdbcDriver;

  @Value("${jdbc.url}")
  String jdbcUrl;

  @Value("${jdbc.username}")
  String jdbcUsername;

  @Value("${jdbc.password}")
  String jdbcPassword;

  @Bean
  public DataSource dataSource() {
    BasicDataSource ds = new BasicDataSource();
    ds.setDriverClassName(jdbcDriver);
    ds.setUrl(jdbcUrl);
    ds.setUsername(jdbcUsername);
    ds.setPassword(jdbcPassword);

    return ds;
  }

  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    // 필요한 값이 있다면 이렇게 파라미터로 선언만 하라.
    // 단 IoC 컨테이너에 들어있는 값이어야 한다.
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource, // DB 커넥션 풀
      ApplicationContext appCtx // Spring IoC 컨테이너
      ) throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    // sqlSessionFactoryBean.setTypeAliasesPackage("com.eomcs.spring.ioc.ex12");
    sqlSessionFactoryBean.setTypeAliases(Board.class);
    sqlSessionFactoryBean.setMapperLocations(
        // Spring IoC 컨테이너를 통해 SQL 맵퍼 파일의 위치 정보를 가져온다.
        appCtx.getResources("classpath:com/eomcs/spring/ioc/ex12/e/*Mapper.xml"));

    return sqlSessionFactoryBean.getObject(); // .getObject: SqlSessionFactory 객체를 리턴한다.
  }
}