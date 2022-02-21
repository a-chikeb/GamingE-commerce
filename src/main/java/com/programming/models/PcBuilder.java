package com.programming.models;


import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "pcBuilder")
public class PcBuilder {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    @Column(name = "date")
    private Date date;

    @Nullable
    @Column(name = "cpu")
    private int cpu;

    @Nullable
    @Column(name = "fan")
    private int fan;

    @Nullable
    @Column(name = "memory")
    private int memory;

    @Nullable
    @Column(name = "graphicsCard")
    private int graphicsCard;

    @Nullable
    @Column(name = "ssd")
    private int ssd;

    @Nullable
    @Column(name = "hardDrive")
    private int hardDrive;


    @Nullable
    @Column(name = "motherBoard")
    private int motherBoard;

    //case because case is java keyword
    @Nullable
    @Column(name = "cases")
    private int cases;

    @Nullable
    @Column(name = "powerSupply")
    private int powerSupply;

    @Nullable
    @Column(name = "opticalDrive")
    private int opticalDrive;

    @Nullable
    @Column(name = "soundCard")
    private int soundCard;

    @Nullable
    @Column(name = "networkCard")
    private int networkCard;

    //OPTIONS & ACCESSORIES :

    @Nullable
    @Column(name = "assembly")
    private int assembly;

    @Nullable
    @Column(name = "operatingSystem")
    private int operatingSystem;

    @Nullable
    @Column(name = "sataCable")
    private int sataCable;

    @Nullable
    @Column(name = "caseFan")
    private int caseFan;


    // PERIPHERALS

    @Nullable
    @Column(name = "mouse")
    private int mouse;

    @Nullable
    @Column(name = "keyword")
    private int keyword;

    @Nullable
    @Column(name = "keywordPack")
    private int keywordPack;

    //if have keyboard and mouse pack
    //private int keyboardMousePack;

    @Nullable
    @Column(name = "monitor")
    private int monitor;

    @Nullable
    @Column(name = "speakers")
    private int speakers;

    @Nullable
    @Column(name = "webcam")
    private int webcam;

    @Nullable
    @Column(name = "usbFlashDrive")
    private int usbFlashDrive;

    @Nullable
    @Column(name = "mousePad")
    private int mousePad;

    @Nullable
    @Column(name = "headsetAndMicrophone")
    private int headsetAndMicrophone;

    @Nullable
    @Column(name = "printer")
    private int printer;

    @Nullable
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public PcBuilder() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCpu() {
        return cpu;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public int getFan() {
        return fan;
    }

    public void setFan(int fan) {
        this.fan = fan;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(int graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public int getSsd() {
        return ssd;
    }

    public void setSsd(int ssd) {
        this.ssd = ssd;
    }

    public int getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(int hardDrive) {
        this.hardDrive = hardDrive;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(int powerSupply) {
        this.powerSupply = powerSupply;
    }

    public int getOpticalDrive() {
        return opticalDrive;
    }

    public void setOpticalDrive(int opticalDrive) {
        this.opticalDrive = opticalDrive;
    }

    public int getSoundCard() {
        return soundCard;
    }

    public void setSoundCard(int soundCard) {
        this.soundCard = soundCard;
    }

    public int getNetworkCard() {
        return networkCard;
    }

    public void setNetworkCard(int networkCard) {
        this.networkCard = networkCard;
    }

    public int getAssembly() {
        return assembly;
    }

    public void setAssembly(int assembly) {
        this.assembly = assembly;
    }

    public int getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(int operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public int getSataCable() {
        return sataCable;
    }

    public void setSataCable(int sataCable) {
        this.sataCable = sataCable;
    }

    public int getCaseFan() {
        return caseFan;
    }

    public void setCaseFan(int caseFan) {
        this.caseFan = caseFan;
    }

    public int getMouse() {
        return mouse;
    }

    public void setMouse(int mouse) {
        this.mouse = mouse;
    }

    public int getKeyword() {
        return keyword;
    }

    public void setKeyword(int keyword) {
        this.keyword = keyword;
    }

    public int getMonitor() {
        return monitor;
    }

    public void setMonitor(int monitor) {
        this.monitor = monitor;
    }

    public int getSpeakers() {
        return speakers;
    }

    public void setSpeakers(int speakers) {
        this.speakers = speakers;
    }

    public int getWebcam() {
        return webcam;
    }

    public void setWebcam(int webcam) {
        this.webcam = webcam;
    }

    public int getUsbFlashDrive() {
        return usbFlashDrive;
    }

    public void setUsbFlashDrive(int usbFlashDrive) {
        this.usbFlashDrive = usbFlashDrive;
    }

    public int getMousePad() {
        return mousePad;
    }

    public void setMousePad(int mousePad) {
        this.mousePad = mousePad;
    }

    public int getHeadsetAndMicrophone() {
        return headsetAndMicrophone;
    }

    public void setHeadsetAndMicrophone(int headsetAndMicrophone) {
        this.headsetAndMicrophone = headsetAndMicrophone;
    }

    public int getPrinter() {
        return printer;
    }

    public void setPrinter(int printer) {
        this.printer = printer;
    }

    /*
    public User getUser() {
        return user;
    }

     */

    public void setUser(User user) {
        this.user = user;
    }


    public int getMotherBoard() {
        return motherBoard;
    }

    public void setMotherBoard(int motherBoard) {
        this.motherBoard = motherBoard;
    }

    public int getKeywordPack() {
        return keywordPack;
    }

    public void setKeywordPack(int keywordPack) {
        this.keywordPack = keywordPack;
    }
}
