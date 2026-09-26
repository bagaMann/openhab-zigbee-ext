# openHAB Zigbee Ext

Расширение Zigbee binding для openHAB с поддержкой дополнительных пользовательских конвертеров устройств без изменения штатного `org.openhab.binding.zigbee`.

## Целевая среда

- openHAB 5.2.1
- штатный openHAB Zigbee binding
- координатор ZB-GW04 v1.2
- первое тестовое устройство: Shelly WS90

## Цель первого этапа

Проверить возможность подключения отдельного OSGi bundle, реализующего публичный `ZigBeeChannelConverterProvider`, и регистрации дополнительного `ZigBeeBaseChannelConverter` в штатном Zigbee binding.

После успешного PoC планируется перейти к внешним декларативным определениям устройств (аналог external converters Zigbee2MQTT), не требующим компиляции Java-кода для каждого нового устройства.

Подробности архитектуры находятся в `docs/architecture.md`.
