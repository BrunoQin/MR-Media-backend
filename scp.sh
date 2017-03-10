#!/usr/bin/expect
set timeout 20

if { [llength $argv] < 2} {
    puts "Usage:"
    puts "$argv0 local_file remote_path"
    exit 1
}

set local_file [lindex $argv 0]
set remote_path [lindex $argv 1]

set passwd Qinbo960415!

set passwderror 0

spawn scp $local_file $remote_path

expect {
    "*assword:*" {
        if { $passwderror == 1 } {
        puts "passwd is error"
        exit 2
        }
        set timeout 1000
        set passwderror 1
        send "$passwd\r"
        exp_continue
    }
    "*es/no)?*" {
        send "yes\r"
        exp_continue
    }
    timeout {
        puts "connect is timeout"
        exit 3
    }
}
