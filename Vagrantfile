# -*- mode: ruby -*-
# vi: set ft=ruby :
#


Vagrant.configure("2") do |config|

  $init = <<-EOF
    mkdir -p /root/.ssh/
    chmod 700 /root/.ssh/
    echo "ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIKXdppNp2Ydew5XIkC+dCqJ9HTU7RE4yENpdxO6OQkmk kmille@linbox" > /root/.ssh/authorized_keys
    chmod 600 /root/.ssh/authorized_keys
    echo "Hey! My name is $(hostname -f) and you can talk to me using: $(hostname -I)"
  EOF
  

  if Vagrant.has_plugin?("vagrant-vbguest")
    config.vbguest.auto_update = false
  end


  config.vm.define "lb01" do |vmConfig|
    vmConfig.vm.box = "ubuntu/bionic64" # 18.04
    vmConfig.vm.provision "shell", inline: $init, privileged: true
    vmConfig.vm.network "private_network", ip: "192.168.33.11"
    vmConfig.vm.hostname = "kmille-solutions-lb01.cloud.dev"

    vmConfig.vm.provider "virtualbox" do |vb|
      vb.name = "lb01"
      vb.cpus = 1
      vb.memory = 512
      vb.linked_clone = true
    end
  end
  
  config.vm.define "lb02" do |vmConfig|
    vmConfig.vm.box = "ubuntu/bionic64"
    vmConfig.vm.provision "shell", inline: $init, privileged: true
    vmConfig.vm.network "private_network", ip: "192.168.33.12"
    vmConfig.vm.hostname = "kmille-solutions-lb02.cloud.dev"

    vmConfig.vm.provider "virtualbox" do |vb|
      vb.name = "lb02"
      vb.cpus = 1
      vb.memory = 512
      vb.linked_clone = true
    end
  end
  
  
  config.vm.define "app01" do |vmConfig|
    vmConfig.vm.box = "ubuntu/xenial64" # 16.04
    vmConfig.vm.network "forwarded_port", guest: 8080, host: 8080

    vmConfig.vm.provision "shell", inline: $init, privileged: true
    vmConfig.vm.network "private_network", ip: "192.168.33.21"
    vmConfig.vm.hostname = "kmille-solutions-app01.cloud.dev"

    vmConfig.vm.provider "virtualbox" do |vb|
      vb.name = "app01"
      vb.cpus = 1
      vb.memory = 512
      vb.linked_clone = true
    end
  end
  
  
  config.vm.define "db01" do |vmConfig|
    vmConfig.vm.box = "ubuntu/bionic64" 

    vmConfig.vm.provision "shell", inline: $init, privileged: true
    vmConfig.vm.network "private_network", ip: "192.168.33.31"
    vmConfig.vm.hostname = "kmille-solutions-db01.cloud.dev"

    vmConfig.vm.provider "virtualbox" do |vb|
      vb.name = "db01"
      vb.cpus = 1
      vb.memory = 1024
      vb.linked_clone = true
    end
  end

end
