package cc.blynk.server.core.model.widgets.ui.reporting.source;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static cc.blynk.server.internal.EmptyArraysUtil.EMPTY_INTS;

/**
 * The Blynk Project.
 * Created by Dmitriy Dumanskiy.
 * Created on 22.05.18.
 */
public class TileTemplateReportSource extends ReportSource {

    public final int templateId;

    public volatile int[] deviceIds;

    @JsonCreator
    public TileTemplateReportSource(@JsonProperty("dataStreams") ReportDataStream[] reportDataStream,
                                    @JsonProperty("templateId") int templateId,
                                    @JsonProperty("deviceIds") int[] deviceIds) {
        super(reportDataStream);
        this.templateId = templateId;
        this.deviceIds = deviceIds == null ? EMPTY_INTS : deviceIds;
    }

    @Override
    public boolean isValid() {
        return deviceIds.length > 0 && super.isValid();
    }

    @Override
    public int[] getDeviceIds() {
        return deviceIds;
    }

    @Override
    public void deleteDevice(int deviceId) {
        this.deviceIds = deleteDeviceFromArray(this.deviceIds, deviceId);
    }
}
