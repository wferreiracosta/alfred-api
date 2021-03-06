package com.wferreiracosta.alfred.controllers.exception;

import static org.assertj.core.api.Assertions.assertThat;

import com.wferreiracosta.alfred.controllers.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FieldMessageTest extends ControllerTest {
  private final String FIELDNAME = "msg";
	private final String MESSAGE = "Teste";

  @Test
  @DisplayName("Deve criar o objeto usando o metodo Setter")
  public void criarObjetoAtravesDoSetter(){
    FieldMessage fieldMessage = new FieldMessage();
    fieldMessage.setFieldName(FIELDNAME);
    fieldMessage.setMessage(MESSAGE);

    assertThat(fieldMessage.getFieldName()).isEqualTo(FIELDNAME);
    assertThat(fieldMessage.getMessage()).isEqualTo(MESSAGE);
  }

  @Test
  @DisplayName("Deve criar objeto atraves do construtor")
  public void criarObjetoAtravesDoConstrutor(){
    FieldMessage fieldMessage = new FieldMessage(FIELDNAME, MESSAGE);

    assertThat(fieldMessage.getFieldName()).isEqualTo(FIELDNAME);
    assertThat(fieldMessage.getMessage()).isEqualTo(MESSAGE);
  }
}
