package io.github.bagamann.openhab.zigbee.ext;

import java.util.Collections;
import java.util.Map;

import org.openhab.binding.zigbee.converter.ZigBeeBaseChannelConverter;
import org.openhab.binding.zigbee.converter.ZigBeeChannelConverterProvider;
import org.openhab.core.thing.type.ChannelTypeUID;

/**
 * External Zigbee converter provider.
 *
 * <p>The first PoC intentionally publishes an empty converter map. Its purpose
 * is to prove that a separately installed OSGi bundle can resolve against the
 * official Zigbee binding and register a ZigBeeChannelConverterProvider service.
 *
 * <p>The OSGi Declarative Services component is declared explicitly in
 * {@code OSGI-INF/external-channel-converter-provider.xml}. This mirrors the
 * manifest style used by the openHAB runtime and avoids adding an unnecessary
 * resolver requirement for a DS extender capability.
 */
public final class ExternalChannelConverterProvider implements ZigBeeChannelConverterProvider {

    public ExternalChannelConverterProvider() {
    }

    @Override
    public Map<ChannelTypeUID, Class<? extends ZigBeeBaseChannelConverter>> getChannelConverters() {
        return Collections.emptyMap();
    }
}
