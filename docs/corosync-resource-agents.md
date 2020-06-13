# Resource Agents

### overview classes ###
crm ra classes


root@kmille-solutions-lb01:~# crm ra classes
lsb
ocf / .isolation heartbeat pacemaker redhat
service
stonith
systemd


root@kmille-solutions-lb01:~# crm ra list lsb
acpid                apache-htcacheclean  apache2              apparmor             apport               atd                  console-setup.sh     corosync
cron                 cryptdisks           cryptdisks-early     dbus                 ebtables             grub-common          haproxy              hwclock.sh
irqbalance           iscsid               keyboard-setup.sh    kmod                 logd                 lvm2                 lvm2-lvmetad         lvm2-lvmpolld
lxcfs                lxd                  mdadm                mdadm-waitidle       open-iscsi           open-vm-tools        openhpid             pacemaker
plymouth             plymouth-log         procps               rsync                rsyslog              screen-cleanup       ssh                  udev
ufw                  unattended-upgrades  uuidd    


root@kmille-solutions-lb01:~# crm ra list systemd |head
accounts-daemon                  acpid                            acpid.socket                     apache-htcacheclean              apache-htcacheclean@
apache2                          apache2@                         apparmor                         apport                           apport-autoreport
apport-forward.socket            apport-forward@                  apt-daily                        apt-daily-upgrade                atd
autovt@                          blk-availability                 bootlogd                         bootlogs                         bootmisc
checkfs                          checkroot                        checkroot-bootclean              cloud-config                     cloud-final
cloud-init                       cloud-init-local                 console-getty                    console-setup                    container-getty@
corosync                         cron                             cryptdisks                       cryptdisks-early                 dbus
dbus-org.freedesktop.hostname1   dbus-org.freedesktop.locale1     dbus-org.freedesktop.login1      dbus-org.freedesktop.resolve1    dbus-org.freedesktop.timedate1
dbus.socket                      debug-shell                      dm-event                         dm-event.socket                  ebtables
emergency                        friendly-recovery                fstrim                           fuse                             getty-static
root@kmille-solutions-lb01:~# 


root@kmille-solutions-lb01:~# crm ra list ocf |head
ASEHAagent.sh        AoEtarget            AudibleAlarm         CTDB                 ClusterMon           Delay                Dummy                EvmsSCC
Evmsd                Filesystem           HealthCPU            HealthSMART          ICP                  IPaddr               IPaddr2              IPsrcaddr
IPv6addr             LVM                  LVM-activate         LinuxSCSI            MailTo               ManageRAID           ManageVE             NodeUtilization
Pure-FTPd            Raid1                Route                SAPDatabase          SAPInstance          SendArp              ServeRAID            SphinxSearchDaemon
Squid                Stateful             SysInfo              SystemHealth         VIPArip              VirtualDomain        WAS                  WAS6
WinPopup             Xen                  Xinetd               ZFS                  anything             apache               apache.sh            asterisk
attribute            aws-vpc-move-ip      aws-vpc-route53      awseip               awsvip               bind-mount.sh        clusterfs.sh         clvm
conntrackd           controld             db2                  db2.sh               dhcpd                dnsupdate            docker               eDir88
ethmonitor           exportfs             fio                  fs.sh                galera               garbd                iSCSILogicalUnit     iSCSITarget
ids                  iface-bridge         iface-vlan           ifspeed              ip.sh                iscsi                jboss                kamailio
root@kmille-solutions-lb01:~# 

/usr/lib/ocf/resource.d/heartbeat/

part of package resource-agents

root@kmille-solutions-lb01:/usr/lib/ocf/resource.d/heartbeat# cat IPaddr2 |grep  'OCF parameters' -A 10
#       OCF parameters are as below
#       OCF_RESKEY_ip
#       OCF_RESKEY_broadcast
#       OCF_RESKEY_nic


crm ra info ocf:heartbeat:IPaddr2
info und meta ist das selbe


root@kmille-solutions-lb01:~# crm ra info ocf:IPaddr2 |head
Manages virtual IPv4 and IPv6 addresses (Linux specific version) (ocf:heartbeat:IPaddr2)

This Linux-specific resource manages IP alias IP addresses.
It can add an IP alias, or remove one.
In addition, it can implement Cluster Alias IP functionality
if invoked as a clone resource.

If used as a clone, you should explicitly set clone-node-max >= 2,
and/or clone-max < number of nodes. In case of node failure,
clone instances need to be re-allocated on surviving nodes.
root@kmille-solutions-lb01:~# 


root@kmille-solutions-lb01:~# crm ra info systemd:haproxy
systemd unit file for haproxy (systemd:haproxy)

HAProxy Load Balancer

Operations' defaults (advisory minimum):

    start         timeout=100
    stop          timeout=100
    status        timeout=100
    monitor       timeout=100 interval=60
