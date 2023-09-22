@Library('pipeline-build-library-blaster') _
buildAppiumForAndroid {
    ownership = [
       solutionName: "Appium for Android",
       type: "Product",
       primaryContact: ["quy.buithi@positivethinking.tech"],
  ]
  
	mail_notification_cicd = "quy.buithi@positivethinking.tech"
	node_label = "seleniumdaeie"
	maven_skip_tests_failure = false
	execution_mode = "Desktop"
	browser_mode = "CHROME"
	TestingType="Regression"
	test_environment="Q"
	num_builds=30
	timeout_value=28
	jira_test_environment="UAT/Q/B2C"
	branch_mapping_to_schedule_cron_expression=["develop":"30 17 * * *"]
	browser_list = ["CHROME"]
}
