package net.demilich.metastone.game.cards.desc;

import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.demilich.metastone.game.GameTag;

public class AttributeDeserializer implements JsonDeserializer<Map<GameTag, Object>> {

	@Override
	public Map<GameTag, Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		Map<GameTag, Object> map = new EnumMap<GameTag, Object>(GameTag.class);
		JsonObject jsonData = json.getAsJsonObject();
		parseAttribute(GameTag.OVERLOAD, jsonData, map, ParseValueType.INTEGER);
		parseAttribute(GameTag.ATTACK_EQUALS_HP, jsonData, map, ParseValueType.BOOLEAN);
		parseAttribute(GameTag.CANNOT_ATTACK, jsonData, map, ParseValueType.BOOLEAN);
		parseAttribute(GameTag.ENRAGABLE, jsonData, map, ParseValueType.BOOLEAN);
		parseAttribute(GameTag.CHARGE, jsonData, map, ParseValueType.BOOLEAN);
		parseAttribute(GameTag.DIVINE_SHIELD, jsonData, map, ParseValueType.BOOLEAN);
		parseAttribute(GameTag.STEALTH, jsonData, map, ParseValueType.BOOLEAN);
		parseAttribute(GameTag.UNTARGETABLE_BY_SPELLS, jsonData, map, ParseValueType.BOOLEAN);
		parseAttribute(GameTag.WINDFURY, jsonData, map, ParseValueType.BOOLEAN);
		parseAttribute(GameTag.MEGA_WINDFURY, jsonData, map, ParseValueType.BOOLEAN);
		parseAttribute(GameTag.TAUNT, jsonData, map, ParseValueType.BOOLEAN);
		parseAttribute(GameTag.SPELL_DAMAGE, jsonData, map, ParseValueType.INTEGER);
		parseAttribute(GameTag.SPELL_AMPLIFY_MULTIPLIER, jsonData, map, ParseValueType.INTEGER);
		parseAttribute(GameTag.INVERT_HEALING, jsonData, map, ParseValueType.BOOLEAN);
		parseAttribute(GameTag.HP, jsonData, map, ParseValueType.INTEGER);
		parseAttribute(GameTag.MAX_HP, jsonData, map, ParseValueType.INTEGER);
		parseAttribute(GameTag.BATTLECRY, jsonData, map, ParseValueType.BOOLEAN);
		parseAttribute(GameTag.DOUBLE_DEATHRATTLES, jsonData, map, ParseValueType.BOOLEAN);
		parseAttribute(GameTag.HERO_POWER_CAN_TARGET_MINIONS, jsonData, map, ParseValueType.BOOLEAN);
		parseAttribute(GameTag.DESTROYED, jsonData, map, ParseValueType.BOOLEAN);
		return map;
	}

	private void parseAttribute(GameTag attribute, JsonObject jsonData, Map<GameTag, Object> map, ParseValueType valueType) {
		String argName = attribute.toString();
		if (!jsonData.has(argName)) {
			return;
		}
		Object value = ParseUtils.parse(argName, jsonData, valueType);
		Boolean bool = value instanceof Boolean ? (Boolean) value : null;
		if (bool != null && bool == true) {
			value = 1;
		}
		map.put(attribute, value);
	}

}
