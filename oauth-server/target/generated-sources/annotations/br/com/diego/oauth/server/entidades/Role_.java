package br.com.diego.oauth.server.entidades;

import br.com.diego.oauth.server.entidades.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-02-21T09:30:16")
@StaticMetamodel(Role.class)
public class Role_ { 

    public static volatile SingularAttribute<Role, String> sigla;
    public static volatile SingularAttribute<Role, String> nome;
    public static volatile SingularAttribute<Role, Long> id;
    public static volatile SetAttribute<Role, Usuario> usuarios;

}