package com.art.xuptbbs.mapper.typehandler;


import com.art.xuptbbs.enumeration.BaseEnum;
import com.art.xuptbbs.enumeration.Board;
import com.art.xuptbbs.enumeration.Classification;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用枚举转换器
 * referer: https://blog.51cto.com/7266799/2171917
 *
 * @param <E>
 */

@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes({Board.class, Classification.class})
public class IntegerEnumHandler<E extends BaseEnum> extends BaseTypeHandler<E> {
    private Map<Integer, E> enumMap = new HashMap<>();

    /**
     * 不加这个的话你会哭的
     */
    public IntegerEnumHandler() {
    }

    public IntegerEnumHandler(Class<E> type) {
        if (type == null)
            throw new IllegalArgumentException("Type argument cannot be null");
        var enums = type.getEnumConstants();
        if (enums == null)
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        for (var e : enums) {
            enumMap.put(e.getvalue(), e);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getvalue());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return get(rs.getInt(columnName));
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return get(rs.getInt(columnIndex));
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return get(cs.getInt(columnIndex));
    }

    private E get(Integer v) {
        if (v == null) {
            return null;
        }
        return this.enumMap.get(v);

    }
}
