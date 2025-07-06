import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import {Button,ComboBox,Dialog,Grid,GridColumn,GridItemModel,TextField,VerticalLayout}
from '@vaadin/react-components';
import { Notification } from '@vaadin/react-components/Notification';
import { GeneroService } from 'Frontend/generated/endpoints';
import { useSignal } from '@vaadin/hilla-react-signals';
import handleError from 'Frontend/views/_ErrorHandler';
import { Group, ViewToolbar } from 'Frontend/components/ViewToolbar';
import { useDataProvider } from '@vaadin/hilla-react-crud';
import Genero from 'Frontend/generated/org/unl/music/base/models/Genero';
import { useEffect } from 'react';

export const config: ViewConfig = {
  title: 'Genero',
  menu: {
    icon: 'vaadin:clipboard-check',
    order: 1,
    title: 'Generos',
  },
};


type GeneroEntryFormProps = {
  onGeneroCreated?: () => void;
};

type GeneroEntryFormPropsUpdate = ()=> {
  onGeneroUpdated?: () => void;
};
//GUARDAR ARTISTA
function GeneroEntryForm(props: GeneroEntryFormProps) {
  const nombre = useSignal('');

  const createGenero = async () => {
    try {
      if (nombre.value.trim().length > 0) {
        await GeneroService.createGenero(nombre.value);
        if (props.onGeneroCreated) {
          props.onGeneroCreated();
        }
        nombre.value = '';
        dialogOpened.value = false;
        Notification.show('Genero creado', { duration: 5000, position: 'bottom-end', theme: 'success' });
      } else {
        Notification.show('No se pudo crear, faltan datos', { duration: 5000, position: 'top-center', theme: 'error' });
      }

    } catch (error) {
      console.log(error);
      handleError(error);
    }
  };

  const dialogOpened = useSignal(false);
  return (
    <>
      <Dialog
        modeless
        headerTitle="Nuevo Genero"
        opened={dialogOpened.value}
        onOpenedChanged={({ detail }) => {
          dialogOpened.value = detail.value;
        }}
        footer={
          <>
            <Button
              onClick={() => {
                dialogOpened.value = false;
              }}
            >
              Candelar
            </Button>
            <Button onClick={createGenero} theme="primary">
              Registrar
            </Button>

          </>
        }
      >
        <VerticalLayout style={{ alignItems: 'stretch', width: '18rem', maxWidth: '100%' }}>
          <TextField label="Nombre del Genero"
            placeholder="Ingrese el nombre del Genero"
            aria-label="Nombre del Genero"
            value={nombre.value}
            onValueChanged={(evt) => (nombre.value = evt.detail.value)}
          />

        </VerticalLayout>
      </Dialog>
      <Button
            onClick={() => {
              dialogOpened.value = true;
            }}
          >
            Agregar
          </Button>
    </>
  );
}

//GUARDAR ARTISTA
const GeneroEntryFormUpdate = function(props: GeneroEntryFormPropsUpdate){//useCallback((props: ArtistaEntryFormPropsUpdate,{ item: art }: { item: Artista }) => {
  console.log(props);
const nombre = useSignal('');

  const createGenero = async () => {
    try {
      if (nombre.value.trim().length > 0) {
        await GeneroService.updateGenero(props.arguments.id, nombre.value);
        if (props.arguments.onGeneroUpdated) {
          props.arguments.onGeneroUpdated();
        }
        nombre.value = '';
        dialogOpened.value = false;
        Notification.show('Genero creado', { duration: 5000, position: 'bottom-end', theme: 'success' });
      } else {
        Notification.show('No se pudo crear, faltan datos', { duration: 5000, position: 'top-center', theme: 'error' });
      }

    } catch (error) {
      console.log(error);
      handleError(error);
    }
  };


  const dialogOpened = useSignal(false);
  return (
    <>
      <Dialog
        modeless
        headerTitle="Actualizar Genero"
        opened={dialogOpened.value}
        onOpenedChanged={({ detail }) => {
          dialogOpened.value = detail.value;
        }}
        footer={
          <>
            <Button
              onClick={() => {
                dialogOpened.value = false;
              }}
            >
              Candelar
            </Button>
            <Button onClick={createGenero} theme="primary">
              Registrar
            </Button>

          </>
        }
      >
        <VerticalLayout style={{ alignItems: 'stretch', width: '18rem', maxWidth: '100%' }}>
          <TextField label="Nombre del Genero"
            placeholder="Ingrese el nombre del Genero"
            aria-label="Nombre del Genero"
            value={nombre.value}
            onValueChanged={(evt) => (nombre.value = evt.detail.value)}
          />

        </VerticalLayout>
      </Dialog>
      <Button
            onClick={() => {
              dialogOpened.value = true;
            }}
          >
            Editar
          </Button>
    </>
  );
};


//LISTA DE ARTISTAS
export default function GeneroView() {

  const dataProvider = useDataProvider<Genero>({
    list: () => GeneroService.listAll(),
  });

  function indexLink({ item}: { item: Genero }) {

    return (
      <span>
        <GeneroEntryFormUpdate arguments={item} onGeneroUpdated={dataProvider.refresh}>

          </GeneroEntryFormUpdate>
      </span>
    );
  }

  function indexIndex({model}:{model:GridItemModel<Genero>}) {
    return (
      <span>
        {model.index + 1}
      </span>
    );
  }

  return (

    <main className="w-full h-full flex flex-col box-border gap-s p-m">

      <ViewToolbar title="Lista de Genero">
        <Group>
          <GeneroEntryForm onGeneroCreated={dataProvider.refresh}/>
        </Group>
      </ViewToolbar>
      <Grid dataProvider={dataProvider.dataProvider}>
        <GridColumn path="nombre" header="Nombre del Genero" />
        <GridColumn header="Acciones" renderer={indexLink}/>
      </Grid>
    </main>
  );
}