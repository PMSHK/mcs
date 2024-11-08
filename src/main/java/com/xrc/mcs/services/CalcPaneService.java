package com.xrc.mcs.services;

import com.xrc.mcs.data.GeneralData;
import com.xrc.mcs.data.PaneSubject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class CalcPaneService {
    private static int id = 0;
    private Map<Integer, GeneralData> generalData;

    public void addNewPane() {
        if (generalData == null) {
            generalData = new HashMap<>();
        }
        PaneSubject paneSubject = new PaneSubject(id);
        generalData.put(id, paneSubject.getGeneralData());
        id++;
    }
}
