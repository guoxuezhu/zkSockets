package com.lh.zksockets.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class SerialCommand {

    @Id
    public Long sKey;

    public int sId;

    public int mlId;

    public String commandId;

    public String commandName;

    public String commandStr;

    @Generated(hash = 1701474513)
    public SerialCommand(Long sKey, int sId, int mlId, String commandId,
            String commandName, String commandStr) {
        this.sKey = sKey;
        this.sId = sId;
        this.mlId = mlId;
        this.commandId = commandId;
        this.commandName = commandName;
        this.commandStr = commandStr;
    }

    @Generated(hash = 2087779334)
    public SerialCommand() {
    }

    @Override
    public String toString() {
        return "SerialCommand{" +
                "sKey=" + sKey +
                ", sId=" + sId +
                ", mlId=" + mlId +
                ", commandId='" + commandId + '\'' +
                ", commandName='" + commandName + '\'' +
                ", commandStr='" + commandStr + '\'' +
                '}';
    }

    public Long getSKey() {
        return this.sKey;
    }

    public void setSKey(Long sKey) {
        this.sKey = sKey;
    }

    public int getSId() {
        return this.sId;
    }

    public void setSId(int sId) {
        this.sId = sId;
    }

    public int getMlId() {
        return this.mlId;
    }

    public void setMlId(int mlId) {
        this.mlId = mlId;
    }

    public String getCommandId() {
        return this.commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getCommandName() {
        return this.commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandStr() {
        return this.commandStr;
    }

    public void setCommandStr(String commandStr) {
        this.commandStr = commandStr;
    }
}