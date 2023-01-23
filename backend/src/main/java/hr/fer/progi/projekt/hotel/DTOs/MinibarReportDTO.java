package hr.fer.progi.projekt.hotel.DTOs;

import java.util.Map;

public class MinibarReportDTO {

    private String roomName;

    private Map<String, Integer> minibarList;

    public MinibarReportDTO() {
    }

    public MinibarReportDTO(String roomName, Map<String, Integer> minibarList) {
        this.roomName = roomName;
        this.minibarList = minibarList;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Map<String, Integer> getMinibarList() {
        return minibarList;
    }

    public void setMinibarList(Map<String, Integer> minibarList) {
        this.minibarList = minibarList;
    }
}
