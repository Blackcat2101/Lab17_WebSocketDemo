import { useAppSelector } from "../store/hooks.ts";
import {
  selectWebSocket,
  messageType,
} from "../store/Slices/webSocketSlice.ts";

const OnlineUser = () => {
  const webSocketState = useAppSelector(selectWebSocket);

  const countPlayer =
    webSocketState.messages?.reduce((total, message) => {
      if (
        message.type === messageType.JOIN ||
        message.type === messageType.LEAVE
      ) {
        return (total = message.count);
      }
      return total;
    }, 0) || 0;

  return (
    <p>
      Online persons : <strong>{countPlayer}</strong>
    </p>
  );
};

export default OnlineUser;
