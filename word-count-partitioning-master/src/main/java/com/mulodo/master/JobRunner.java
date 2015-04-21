package com.mulodo.master;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 * Simple job runner to execute a job without any parameters.
 * </p>
 */
public class JobRunner
{
    private static final String CONFIG = "job-context.xml";

    public static void main(String[] args) throws Exception
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(CONFIG);

        Job job = applicationContext.getBean(Job.class);
        JobLauncher jobLauncher = applicationContext.getBean(JobLauncher.class);

        JobParameters jobParameters = new JobParametersBuilder().addDate("now", new Date())
                .toJobParameters();

        jobLauncher.run(job, jobParameters);

        System.exit(0);
    }
}
