package com.towery.ignite.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager

import javax.sql.DataSource

/**事务配置
 * Created by User on 2016/11/23.
 */
@Configuration
open class TransactionBaseConfiguration {
    @Bean(name = arrayOf("BaseTransactionManager"))
    open fun baseTransactionManager(@Qualifier("base") dataSource: DataSource): DataSourceTransactionManager {
        return DataSourceTransactionManager(dataSource)
    }
}
