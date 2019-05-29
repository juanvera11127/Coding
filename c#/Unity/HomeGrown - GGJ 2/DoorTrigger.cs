using System.Collections;
using System.Collections.Generic;
using UnityEngine;
public class DoorTrigger : MonoBehaviour
{
	public Camera myCamera;
	public Transform linkedDoor;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }
	private void OnTriggerEnter2D(Collider2D collision)
	{

		if (collision.gameObject.tag == "Player")
		{
			GetComponent<AudioSource>().Play();
			foreach (GameObject cam in MCP.mcp.cameras)
			{
				cam.SetActive(false);
			}
			myCamera.gameObject.SetActive(true);
			MCP.player.transform.position = linkedDoor.transform.position;
		}
	}
}
