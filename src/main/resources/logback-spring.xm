<?xml version="1.0" encoding="UTF-8"?>
<configuration>
	<!--定义日志文件的存储地址 勿在 LogBack 的配置中使用相对路径 -->
	<property name="LOG_HOME" value="D:/" />
	<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
		<Target>System.out</Target>
		<encoder>
			<pattern>[%-5p] [%d{yyyy-MM-dd HH:mm:ss}] [%t] [%C{1}:%M:%L] %m%n</pattern>
		</encoder>
		<filter class="ch.qos.logback.classic.filter.ThresholdFilter">
			<level>DEBUG</level>
		</filter>
	</appender>
	 <!-- 级别阀值过滤 INFO -->
	<appender name="DAILY_ROLLING_FILE"
		class="ch.qos.logback.core.rolling.RollingFileAppender">
		<encoder>
			<pattern>[%-5p] [%d{yyyy-MM-dd HH:mm:ss}] [%t] [%C{1}:%M:%L] %m%n</pattern>
		</encoder>
		<filter class="ch.qos.logback.classic.filter.ThresholdFilter">
			<level>INFO</level>
		</filter>
		<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
			<fileNamePattern>/data/logs/xiongmaohaixing/info.%d{yyyy-MM-dd}.log</fileNamePattern>
			<!-- 保存一个月的日志 -->
            <MaxHistory>30</MaxHistory>
		</rollingPolicy>
	</appender>
	 <!-- 级别阀值过滤  ERROR-->
	<appender name="ERROR_DAILY_ROLLING_FILE"
		class="ch.qos.logback.core.rolling.RollingFileAppender">
		<encoder>
			<pattern>[%-5p] [%d{yyyy-MM-dd HH:mm:ss}] [%t] [%C{1}:%M:%L] %m%n</pattern>
		</encoder>
		<filter class="ch.qos.logback.classic.filter.ThresholdFilter">
			<level>ERROR</level>
		</filter>
		<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
			<fileNamePattern>/data/logs/xiongmaohaixing/error.%d{yyyy-MM-dd}.log</fileNamePattern>
			<!-- 保存一个月的日志 -->
            <MaxHistory>30</MaxHistory>
		</rollingPolicy>
	</appender>
	<logger name="org.apache.commons" level="ERROR" />
	<root level="INFO">
		<appender-ref ref="CONSOLE" />
		<appender-ref ref="DAILY_ROLLING_FILE" />
		<appender-ref ref="ERROR_DAILY_ROLLING_FILE" />
	</root>
</configuration>