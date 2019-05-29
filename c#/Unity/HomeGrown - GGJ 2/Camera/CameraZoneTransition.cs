using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraZoneTransition : MonoBehaviour
{
	public GameObject targetCamera;
	public Transform transitionTarget;
	public Vector3 playerPosAdjust;
	public CameraZoneTransition partner;
	public bool followCam = false;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

	//this is very linear, add some smoothing possibly
	public IEnumerator TransitionZone()
	{
		if(!followCam)
			targetCamera.GetComponent<ConstrainedFollow>().enabled = followCam;
		Vector3 startPos = targetCamera.transform.position;
		Vector3 playerStartPos = MCP.player.transform.position;
		MCP.player.GetComponent<PlayerControls>().canControl = false;
		MCP.player.GetComponent<Collider2D>().enabled = false;
		for(float step = 0; step <1; step += 0.01f)
		{
			targetCamera.transform.position = Vector3.Lerp(startPos, transitionTarget.position, step);
			MCP.player.transform.position = Vector3.Lerp(playerStartPos, playerStartPos + playerPosAdjust, step);
			yield return new WaitForSeconds(0.01f);
		}
		MCP.player.GetComponent<Collider2D>().enabled = true;
		if (followCam)
			targetCamera.GetComponent<ConstrainedFollow>().enabled = followCam;
		targetCamera.transform.position = transitionTarget.position;
		MCP.player.GetComponent<PlayerControls>().canControl = true;
	}

	private void OnTriggerEnter2D(Collider2D collision)
	{
		if (collision.gameObject.tag == "Player")
		{
			StartCoroutine(TransitionZone());
		}
	}
}
