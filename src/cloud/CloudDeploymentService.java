package cloud;

import java.time.LocalDateTime;

public class CloudDeploymentService {

    private String cloudProvider;
    private String serverName;
    private boolean deployed;

    public CloudDeploymentService(String cloudProvider,
                                  String serverName) {

        this.cloudProvider = cloudProvider;
        this.serverName = serverName;
        this.deployed = false;
    }

    public void deployApplication() {

        deployed = true;

        System.out.println(
                "\n--- Cloud Deployment Simulation ---"
        );

        System.out.println(
                "Cloud Provider: " + cloudProvider
        );

        System.out.println(
                "Server Name: " + serverName
        );

        System.out.println(
                "Application: SHEMS"
        );

        System.out.println(
                "Deployment Status: Successfully Deployed"
        );

        System.out.println(
                "Deployment Time: " + LocalDateTime.now()
        );
    }

    public void checkServerStatus() {

        if (deployed) {

            System.out.println(
                    "Cloud Server Status: Running"
            );

            System.out.println(
                    "SHEMS Dashboard URL: Simulation only - no live cloud server deployed"
            );

        } else {

            System.out.println(
                    "Cloud Server Status: Not Deployed"
            );
        }
    }

    public boolean isDeployed() {

        return deployed;
    }
}