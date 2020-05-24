# managed hosting lab
Test environment for a typical manged hosting environment used for testing. Easy to use with vagrant and easy to to destroy and re-deploy with a simple ansible playbook.



## Setup

| Hostname                         | IP            | Role               | Software                               |
| :------------------------------- | ------------- | ------------------ | -------------------------------------- |
| kmille-solutions-lb01.cloud.dev  | 192.168.33.11 | Loadbalancer       | corosync/pacemaker + haproxy           |
| kmille-solutions-lb02.cloud.dev  | 192.168.33.12 | Loadbalancer       | corosync/pacemaker + haproxy           |
| kmille-solutions-app01.cloud.dev | 192.168.33.21 | Application Server | tomcat8 hosting a simple java  servlet |
| kmille-solutions-app02.cloud.dev | 192.168.33.22 | Application Server | tomcat8 hosting a simple java  servlet |
| kmille-solutions-db01.cloud.dev  | 192.168.33.31 | Database-Server    | MySQL-Server                           |



## How to use it
```bash
kmille@linbox managed-hosting-testing master % vagrant up
kmille@linbox managed-hosting-testing master % cp config_lab ~/.ssh
kmille@linbox managed-hosting-testing master % # Append `Include ~/.ssh/config_lab` to ~/.ssh/config
kmille@linbox managed-hosting-testing master % ansible-playbook site.yaml
kmille@linbox managed-hosting-testing master % curl -v http://192.168.33.10/api/status 
*   Trying 192.168.33.10:80...
* Connected to 192.168.33.10 (192.168.33.10) port 80 (#0)
> GET /api/status HTTP/1.1
> Host: 192.168.33.10
> User-Agent: curl/7.70.0
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 OK
< Server: Apache-Coyote/1.1
< Content-Type: application/json;charset=ISO-8859-1
< Content-Length: 16
< Date: Sun, 24 May 2020 18:26:26 GMT
< X-Loadbalancer: kmille-solutions-lb02
< X-Backend: app01
< 
{"state": "up"}
* Connection #0 to host 192.168.33.10 left intact

```






## Things to tests
## corosync
- split-brain between loadbalancer
- lb01 sees lb02 but lb02 does not see lb01
