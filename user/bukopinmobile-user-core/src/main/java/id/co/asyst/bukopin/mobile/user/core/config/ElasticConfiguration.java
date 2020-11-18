package id.co.asyst.bukopin.mobile.user.core.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;


/**
 * Elasticsearch Configuration
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 18, 2020
 * @since 1.4.Alpha1
 */
@Configuration
public class ElasticConfiguration extends AbstractElasticsearchConfiguration {

    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNodes;
    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clusterName;
    
    @Override
    public RestHighLevelClient elasticsearchClient() {
	final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
		.connectedTo(clusterNodes)
		.build();

	return RestClients.create(clientConfiguration).rest();
    }  
    
}
