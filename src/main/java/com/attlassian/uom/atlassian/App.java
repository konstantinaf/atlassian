package com.attlassian.uom.atlassian;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws URISyntaxException, IOException, InterruptedException, ExecutionException
    {
        final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        final URI jiraServerUri = new URI("https://konstantinauom.atlassian.net");
        //put password in order to work
        final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, "admin", "*****");
        
        try {
            //get all bugs for specific project key
            Iterable<Issue> issues = restClient.getSearchClient().searchJql("project=COOK and type=bug").get().getIssues();
            
            for (Issue issue : issues )
                System.out.println(issue);
        }
        finally {
            // cleanup the restClient
            restClient.close();
        }
    }
 
  
}
