package co.edu.unbosque.BJCyberNeticForrestM.converter;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import co.edu.unbosque.BJCyberNeticForrestM.PlayListDTO;
import co.edu.unbosque.BJCyberNeticForrestM.model.PlayListBean;

@FacesConverter(forClass = PlayListDTO.class)
public class PlayListConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        PlayListBean playListBean = context.getApplication().evaluateExpressionGet(context, "#{playListBean}", PlayListBean.class);
        return playListBean.buscarPlayListPorNombre(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof PlayListDTO)) {
            return null;
        }

        return ((PlayListDTO) value).getNombre();
    }
}
